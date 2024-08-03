package models

type Personality struct {
	ID          int    `json:"id"`
	PER_NAME    string `json:"PER_NAME"`
	PER_HISTORY string `json:"PER_HISTORY"`
}

var Personalities []Personality
