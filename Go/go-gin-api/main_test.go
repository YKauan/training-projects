package main

import (
	"bytes"
	"encoding/json"
	"io"
	"net/http"
	"net/http/httptest"
	"strconv"
	"testing"

	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/controllers"
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/database"
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/models"
	"github.com/gin-gonic/gin"
	"github.com/stretchr/testify/assert"
)

var ID int

func SetupTestRoutes() *gin.Engine {
	gin.SetMode(gin.ReleaseMode)
	routes := gin.Default()
	return routes
}

func TestCheckUser(t *testing.T) {
	r := SetupTestRoutes()
	r.GET("/:name", controllers.GetUser)

	req, _ := http.NewRequest("GET", "/Jhon", nil)
	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)
	assert.Equal(t, http.StatusOK, res.Code)

	mockedResponse := `{"Hi ":"Jhon"}`
	responseBody, _ := io.ReadAll(res.Body)
	assert.Equal(t, mockedResponse, string(responseBody))
}

func CreateUserMock() {
	userTest := models.User{
		Name:  "Jhon",
		Email: "tst@test.com",
	}
	database.DB.Create(&userTest)
	ID = int(userTest.ID)
}

func DeleteUserMock() {
	var user models.User
	database.DB.Delete(&user, ID)
}

func TestListAllUsers(t *testing.T) {
	database.ConnectDataBase()
	CreateUserMock()
	defer DeleteUserMock()

	r := SetupTestRoutes()
	r.GET("/users", controllers.ListAllUsers)

	req, _ := http.NewRequest("GET", "/users", nil)
	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)
	assert.Equal(t, http.StatusOK, res.Code)
}

func GetUserByEmail(t *testing.T) {
	database.ConnectDataBase()
	CreateUserMock()
	defer DeleteUserMock()

	r := SetupTestRoutes()
	r.GET("/users/email/:email", controllers.GetUserByEmail)

	req, _ := http.NewRequest("GET", "/tst@test.com", nil)
	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)
	assert.Equal(t, http.StatusOK, res.Code)
}

func TestGetUserByID(t *testing.T) {
	database.ConnectDataBase()
	CreateUserMock()
	defer DeleteUserMock()

	r := SetupTestRoutes()
	r.GET("/users/:id", controllers.GetUserById)

	req, _ := http.NewRequest("GET", "/users/"+strconv.Itoa(ID), nil)
	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)
	var userMock models.User
	json.Unmarshal(res.Body.Bytes(), &userMock)
	assert.Equal(t, "Jhon", userMock.Name)
	assert.Equal(t, "tst@test.com", userMock.Email)
}

func TestDeleteUser(t *testing.T) {
	database.ConnectDataBase()
	CreateUserMock()

	r := SetupTestRoutes()
	r.DELETE("/users/:id", controllers.DeleteUser)

	req, _ := http.NewRequest("DELETE", "/users/"+strconv.Itoa(ID), nil)
	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)
	assert.Equal(t, http.StatusOK, res.Code)
}

func TestEditUser(t *testing.T) {
	database.ConnectDataBase()
	CreateUserMock()
	defer DeleteUserMock()

	r := SetupTestRoutes()
	r.PATCH("/users/:id", controllers.EditUser)

	userTest := models.User{
		Name:  "Jhon",
		Email: "tst@test.com",
	}
	reqBody, _ := json.Marshal(userTest)
	req, _ := http.NewRequest("PATCH", "/users/"+strconv.Itoa(ID), bytes.NewBuffer(reqBody))

	res := httptest.NewRecorder()
	r.ServeHTTP(res, req)

	var userMock models.User
	json.Unmarshal(res.Body.Bytes(), &userMock)

	assert.Equal(t, http.StatusOK, res.Code)
	assert.Equal(t, "Jhon", userMock.Name)
	assert.Equal(t, "tst@test.com", userMock.Email)
}
