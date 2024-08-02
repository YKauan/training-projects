package main

import (
	"net/http"

	"github.com/YKauan/training-projects/tree/main/Go/GoPage/configs"
	"github.com/YKauan/training-projects/tree/main/Go/GoPage/routes"
)

func main() {
	err := configs.InicializeConfigs()
	if err != nil {
		panic(err.Error())
	}
	routes.LoadRoutes()
	http.ListenAndServe(":"+configs.Env.PORT, nil)
}
