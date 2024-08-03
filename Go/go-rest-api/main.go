package main

import (
	"fmt"

	"github.com/YKauan/training-projects/tree/main/Go/go-rest-api/database"
	"github.com/YKauan/training-projects/tree/main/Go/go-rest-api/models"
	"github.com/YKauan/training-projects/tree/main/Go/go-rest-api/routes"
)

func main() {

	models.Personalities = []models.Personality{
		{ID: 1, PER_NAME: "Albert Einstein", PER_HISTORY: "Albert Einstein was a German-born theoretical physicist who developed the theory of relativity, one of the two pillars of modern physics (alongside quantum mechanics). His work is also known for its influence on the philosophy of science."},
		{ID: 2, PER_NAME: "Isaac Newton", PER_HISTORY: "Sir Isaac Newton PRS was an English mathematician, physicist, astronomer, theologian, and author who is widely recognised as one of the greatest mathematicians and most influential scientists of all time."},
		{ID: 3, PER_NAME: "Galileo Galilei", PER_HISTORY: "Galileo Galilei was an Italian astronomer, physicist and engineer, sometimes described as a polymath. Galileo has been called the father of observational astronomy, modern physics, and the scientific method."},
	}

	database.ConnectDatabase()

	fmt.Println("Rest API v1.0")
	fmt.Println("Server running at http://localhost:8000")
	routes.HandleResquest()
}
