package configs

import (
	"github.com/joho/godotenv"
	"github.com/kelseyhightower/envconfig"
)

type Configs struct {
	ClientID     string `envconfig:"CLIENT_ID"`
	ClientSecret string `envconfig:"CLIENT_SECRET"`
	RedirectURI  string `envconfig:"REDIRECT_URI"`
	AccessToken  string `envconfig:"ACCESS_TOKEN"`
	RefreshToken string `envconfig:"REFRESH_TOKEN"`
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
