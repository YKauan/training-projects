package entities

import (
	"encoding/json"
	"fmt"

	"fyfysongs/pkg"
)

type Genre struct {
	Genres []string `json:"genres"`
}

func GetGenres() (*Genre, error) {
	const endpoint = "recommendations/available-genre-seeds"

	resp, err := pkg.GetSpotifyData(endpoint)
	if err != nil {
		return nil, fmt.Errorf("error getting genres: %v", err)
	}

	var genres Genre
	err = json.Unmarshal(resp, &genres)
	if err != nil {
		return nil, fmt.Errorf("error unmarshalling genres: %v", err)
	}

	return &genres, nil
}
