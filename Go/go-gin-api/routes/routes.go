package routes

import (
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/controllers"
	"github.com/gin-gonic/gin"
)

func HandleRequests() {
	// Here we are instantiating the router
	r := gin.Default()
	r.LoadHTMLGlob("templates/*")
	r.Static("/assets", "./assets")

	// Here we are defining the route and the handler for the route
	r.GET("/index", controllers.DisplayIndexPage)
	r.GET("/users", controllers.ListAllUsers)
	r.GET("/:name", controllers.GetUser)
	r.GET("/users/:id", controllers.GetUserById)
	r.GET("/users/email/:email", controllers.GetUserByEmail)
	r.POST("/users", controllers.CreateUser)
	r.PATCH("/users/:id", controllers.EditUser)
	r.DELETE("/users/:id", controllers.DeleteUser)

	r.NoRoute(controllers.Display404Page)

	// This will make the router start listening for incoming requests
	r.Run()
}
