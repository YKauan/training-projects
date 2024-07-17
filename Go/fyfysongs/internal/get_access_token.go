package main

import (
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
	"net/url"
	"strings"

	"fyfysongs/configs"
)

type Token struct {
	AccessToken  string `json:"access_token"`
	TokenType    string `json:"token_type"`
	Scope        string `json:"scope"`
	ExpiresIn    int    `json:"expires_in"`
	RefreshToken string `json:"refresh_token"`
}

func main() {
	err := configs.InicializeConfigs()
	FatalError(err, "Error initializing configs")
	GetToken()
}

func GetToken() (string, error) {

	data := url.Values{}
	data.Set("grant_type", "client_credentials")

	req, err := http.NewRequest("POST", "https://accounts.spotify.com/api/token", strings.NewReader(data.Encode()))
	FatalError(err, "Error creating request")

	req.Header.Add("Content-Type", "application/x-www-form-urlencoded")
	req.SetBasicAuth(configs.Env.ClientID, configs.Env.ClientSecret)

	client := &http.Client{}
	resp, err := client.Do(req)
	FatalError(err, "Error making request")

	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	FatalError(err, "Error reading response body")

	// Verifica o código de status da resposta
	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("failed to get token: %s", body)
	}

	// Deserializa a resposta JSON
	var tokenResponse Token
	err = json.Unmarshal(body, &tokenResponse)
	FatalError(err, "Error unmarshalling token")

	return tokenResponse.AccessToken, nil
}

func FatalError(err error, msg string) {
	if err != nil {
		fmt.Println(msg + " : " + err.Error())
		log.Fatal(err)
	}
}
