package controllers

import (
	"log"
	"net/http"
	"strconv"
	"text/template"

	"github.com/YKauan/training-projects/tree/main/Go/GoPage/models"
)

// store the templates
var temp = template.Must(template.ParseGlob("../templates/*.html"))

// Index is a function that renders the index page
func Index(w http.ResponseWriter, r *http.Request) {
	temp.ExecuteTemplate(w, "Index", models.SearchProducts())
}

// New is a function that renders the new page
func New(w http.ResponseWriter, r *http.Request) {
	temp.ExecuteTemplate(w, "New", nil)
}

// Insert is a function that inserts a new product
func Insert(w http.ResponseWriter, r *http.Request) {
	if r.Method == "POST" {
		name := r.FormValue("name")
		description := r.FormValue("description")
		price := r.FormValue("price")
		quantity := r.FormValue("quantity")

		convPrice, err := strconv.ParseFloat(price, 64)
		if err != nil {
			log.Println("Error converting price to float64")
		}

		convQuantity, err := strconv.Atoi(quantity)
		if err != nil {
			log.Println("Error converting quantity to int")
		}

		models.InsertProduct(name, description, convPrice, convQuantity)
	}

	http.Redirect(w, r, "/", http.StatusMovedPermanently)
}

// Edit is a function that renders the edit page
func Edit(w http.ResponseWriter, r *http.Request) {
	productId := r.URL.Query().Get("id")
	product := models.EditProduct(productId)

	temp.ExecuteTemplate(w, "Edit", product)
}

// Update is a function that updates a product
func Update(w http.ResponseWriter, r *http.Request) {
	if r.Method == "POST" {
		id := r.FormValue("id")
		name := r.FormValue("name")
		description := r.FormValue("description")
		price := r.FormValue("price")
		quantity := r.FormValue("quantity")

		convId, err := strconv.Atoi(id)
		if err != nil {
			log.Println("Error converting id to int")
		}

		convPrice, err := strconv.ParseFloat(price, 64)
		if err != nil {
			log.Println("Error converting price to float64")
		}

		convQuantity, err := strconv.Atoi(quantity)
		if err != nil {
			log.Println("Error converting quantity to int")
		}

		models.UpdateProduct(convId, name, description, convPrice, convQuantity)
	}

	http.Redirect(w, r, "/", http.StatusMovedPermanently)
}

// Delete is a function that deletes a product
func Delete(w http.ResponseWriter, r *http.Request) {
	productId := r.URL.Query().Get("id")
	models.DeleteProduct(productId)

	http.Redirect(w, r, "/", http.StatusMovedPermanently)
}
