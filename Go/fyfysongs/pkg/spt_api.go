package pkg

import (
	"fmt"
	"io"
	"log"
	"net/http"

	"fyfysongs/configs"
)

func GetSpotifyData(urlComplementary string) ([]byte, error) {
	configs.InicializeConfigs()
	req, err := http.NewRequest("GET", "https://api.spotify.com/v1/"+urlComplementary, nil)
	FatalError(err, "Error creating request")

	req.Header.Add("Authorization", "Bearer "+configs.Env.AccessToken)

	client := &http.Client{}
	resp, err := client.Do(req)
	FatalError(err, "Error making request")

	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	FatalError(err, "Error reading response body")

	// Verifica o código de status da resposta
	if resp.StatusCode != http.StatusOK {
		return nil, fmt.Errorf("failed to get token: %s", body)
	}

	return body, nil

}

func FatalError(err error, msg string) {
	if err != nil {
		log.Fatal(err)
	}
}
