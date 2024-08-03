package main

import (
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/database"
	"github.com/YKauan/training-projects/tree/main/Go/go-gin-api/routes"
)

func main() {
	database.ConnectDataBase()
	routes.HandleRequests()
}
