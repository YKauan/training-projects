package configs

import (
	"github.com/joho/godotenv"
	"github.com/kelseyhightower/envconfig"
)

type Configs struct {
	USER     string `envconfig:"USER"`
	DBNAME   string `envconfig:"DBNAME"`
	PASSWORD string `envconfig:"PASSWORD"`
	HOST     string `envconfig:"HOST"`
	PORT     string `envconfig:"PORT"`
	SSLMODE  string `envconfig:"SSLMODE"`
}

var Env Configs

func InicializeConfigs() error {
	if err := godotenv.Load(); err != nil {
		return err
	}

	if err := envconfig.Process("", &Env); err != nil {
		return err
	}

	return nil
}
