package db

import (
	"database/sql"

	"github.com/YKauan/training-projects/tree/main/Go/GoPage/configs"
	_ "github.com/lib/pq"
)

// ConnectDataBase is a function that connects to the database
func ConnectDataBase() *sql.DB {
	configs.InicializeConfigs()
	conn := "user=" + configs.Env.USER + " dbname=" + configs.Env.DBNAME + " password=" + configs.Env.PASSWORD + " host=" + configs.Env.HOST + " sslmode=" + configs.Env.SSLMODE

	db, err := sql.Open("postgres", conn)
	if err != nil {
		panic(err.Error())
	}

	return db
}
