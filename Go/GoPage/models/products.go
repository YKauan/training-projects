package models

import (
	"github.com/YKauan/training-projects/tree/main/Go/GoPage/internal/db"
)

type Product struct {
	Id          int
	Name        string
	Description string
	Price       float64
	Quantity    int
}

// SearchProducts is a function that searches for all products
func SearchProducts() []Product {
	db := db.ConnectDataBase()

	selectProducts, err := db.Query("SELECT * FROM products order by pr_name asc")
	if err != nil {
		panic(err.Error())
	}

	p := Product{}
	products := []Product{}

	for selectProducts.Next() {
		var id, quantity int
		var name, description string
		var price float64

		err = selectProducts.Scan(&id, &name, &description, &price, &quantity)
		if err != nil {
			panic(err.Error())
		}

		p.Id = id
		p.Name = name
		p.Description = description
		p.Price = price
		p.Quantity = quantity

		products = append(products, p)
	}

	defer db.Close()

	return products
}

// InsertProduct is a function that inserts a new product
func InsertProduct(name, description string, price float64, quantity int) {
	db := db.ConnectDataBase()

	insertProduct, err := db.Prepare("INSERT INTO products(PR_NAME, PR_DESCRIPTION, PR_PRICE, PR_QUANTITY) VALUES($1, $2, $3, $4)")
	if err != nil {
		panic(err.Error())
	}

	insertProduct.Exec(name, description, price, quantity)

	defer db.Close()
}

// EditProduct is a function that edits a product
func EditProduct(id string) Product {
	db := db.ConnectDataBase()

	selectProduct, err := db.Query("SELECT * FROM products WHERE ID=$1", id)
	if err != nil {
		panic(err.Error())
	}

	p := Product{}

	for selectProduct.Next() {
		var id, quantity int
		var name, description string
		var price float64

		err = selectProduct.Scan(&id, &name, &description, &price, &quantity)
		if err != nil {
			panic(err.Error())
		}

		p.Id = id
		p.Name = name
		p.Description = description
		p.Price = price
		p.Quantity = quantity
	}

	defer db.Close()

	return p
}

// UpdateProduct is a function that updates a product
func UpdateProduct(id int, name, description string, price float64, quantity int) {
	db := db.ConnectDataBase()

	updateProduct, err := db.Prepare("UPDATE products SET PR_NAME=$1, PR_DESCRIPTION=$2, PR_PRICE=$3, PR_QUANTITY=$4 WHERE ID=$5")
	if err != nil {
		panic(err.Error())
	}

	updateProduct.Exec(name, description, price, quantity, id)

	defer db.Close()
}

// DeleteProduct is a function that deletes a product
func DeleteProduct(id string) {
	db := db.ConnectDataBase()

	deleteProduct, err := db.Prepare("DELETE FROM products WHERE ID=$1")
	if err != nil {
		panic(err.Error())
	}

	deleteProduct.Exec(id)

	defer db.Close()
}
