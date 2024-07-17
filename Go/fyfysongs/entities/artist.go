package entities

import (
	"encoding/json"
	"fmt"

	"fyfysongs/pkg"
)

type Artist struct {
	Followers struct {
		Href  interface{} `json:"href"`
		Total int         `json:"total"`
	} `json:"followers"`
	Genres []string `json:"genres"`
	Href   string   `json:"href"`
	ID     string   `json:"id"`
	Images []struct {
		URL    string `json:"url"`
		Height int    `json:"height"`
		Width  int    `json:"width"`
	} `json:"images"`
	Name       string `json:"name"`
	Popularity int    `json:"popularity"`
	Type       string `json:"type"`
	URI        string `json:"uri"`
}

func GetArtist(artistID string) (*Artist, error) {
	resp, err := pkg.GetSpotifyData("artists/" + artistID)
	if err != nil {
		return nil, fmt.Errorf("error getting artist data: %v", err)
	}

	var artist Artist
	err = json.Unmarshal(resp, &artist)
	if err != nil {
		return nil, fmt.Errorf("error unmarshalling artist data: %v", err)
	}

	return &artist, nil
}
