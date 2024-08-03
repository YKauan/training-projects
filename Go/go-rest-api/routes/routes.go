package routes

import (
	"log"
	"net/http"

	"github.com/YKauan/training-projects/tree/main/Go/go-rest-api/controllers"
	"github.com/YKauan/training-projects/tree/main/Go/go-rest-api/middleware"
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func HandleResquest() {
	r := mux.NewRouter()
	r.Use(middleware.ContentTypeMiddleware)
	r.HandleFunc("/", controllers.Home)
	r.HandleFunc("/api-personalities", controllers.GetPersonalities).Methods("GET")
	r.HandleFunc("/api-personalities/{id}", controllers.ReturnPersonalityById).Methods("GET")
	r.HandleFunc("/api-personalities", controllers.CreatePersonality).Methods("POST")
	r.HandleFunc("/api-personalities/{id}", controllers.DeletePersonality).Methods("DELETE")
	r.HandleFunc("/api-personalities/{id}", controllers.EditPersonality).Methods("PUT")
	log.Fatal(http.ListenAndServe(":8000", handlers.CORS(handlers.AllowedOrigins([]string{"*"}))(r)))
}
