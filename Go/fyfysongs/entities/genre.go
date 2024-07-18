package entities

import (
	"encoding/json"
	"fmt"
	"time"

	"fyfysongs/pkg"

	"github.com/wailsapp/wails"
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

func (s *Genre) WailsInit(runtime *wails.Runtime) error {
	go func() {
		for {
			genres, err := GetGenres()
			if err != nil {
				fmt.Println("Error getting genres:", err)
				continue
			}
			runtime.Events.Emit("genres", genres)
			time.Sleep(5 * time.Second)
		}
	}()
	return nil
}
