package main

import (
	"context"
	"fyfysongs/entities"
)

// App struct
type App struct {
	ctx context.Context
}

// NewApp creates a new App application struct
func NewApp() *App {
	return &App{}
}

// startup is called when the app starts. The context is saved
// so we can call the runtime methods
func (a *App) startup(ctx context.Context) {
	a.ctx = ctx
}

// Greet returns a greeting for the given name
func (a *App) Genres(name string) *[]string {
	genres, err := entities.GetGenres()
	if err != nil {
		println("Error: ", err.Error())
	}

	return &genres.Genres
}

// Greet returns a greeting for the given name
func (a *App) Artist(name string) *string {
	artist, errEntities := entities.GetArtist(name)
	if errEntities != nil {
		println("Error: ", errEntities.Error())
	}

	return &artist.Name
}
