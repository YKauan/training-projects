package controllers

import (
	"net/http"

	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/database"
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/models"
	"github.com/gin-gonic/gin"
)

func ListAllUsers(c *gin.Context) {
	var users []models.User
	database.DB.Find(&users)
	c.JSON(http.StatusOK, users)
}

func GetUser(c *gin.Context) {
	name := c.Params.ByName("name")
	c.JSON(http.StatusOK, gin.H{
		"Hi ": name,
	})
}

func GetUserById(c *gin.Context) {
	var user models.User
	id := c.Params.ByName("id")
	database.DB.First(&user, id)
	if user.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{"data": "Record not found"})
		return
	}
	c.JSON(http.StatusOK, user)
}

func GetUserByEmail(c *gin.Context) {
	var user models.User
	email := c.Param("email")
	database.DB.Where(&models.User{Email: email}).First(&user)
	if user.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{"data": "Record not found"})
		return
	}
	c.JSON(http.StatusOK, user)
}

func CreateUser(c *gin.Context) {
	var user models.User
	if err := c.ShouldBindJSON(&user); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	if err := models.ValidateUser(&user); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	database.DB.Create(&user)
	c.JSON(http.StatusCreated, user)
}

func DeleteUser(c *gin.Context) {
	var user models.User
	id := c.Params.ByName("id")
	database.DB.First(&user, id)
	if user.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{"data": "Record not found"})
		return
	}
	database.DB.Delete(&user)
	c.JSON(http.StatusOK, gin.H{"data": "Record deleted successfully"})
}

func EditUser(c *gin.Context) {
	var user models.User
	id := c.Params.ByName("id")
	database.DB.First(&user, id)
	if user.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{"data": "Record not found"})
		return
	}
	if err := c.ShouldBindJSON(&user); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	database.DB.Model(&user).UpdateColumns(user)
	c.JSON(http.StatusOK, user)
}

func DisplayIndexPage(c *gin.Context) {
	var users []models.User
	database.DB.Find(&users)

	c.HTML(http.StatusOK, "index.html", gin.H{
		"users": users,
	})
}

func Display404Page(c *gin.Context) {
	c.HTML(http.StatusNotFound, "404.html", nil)
}