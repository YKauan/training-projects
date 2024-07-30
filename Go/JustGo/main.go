package main

import (
	"github.com/YKauan/training-projects/tree/main/Go/JustGo/game"
	"github.com/hajimehoshi/ebiten/v2"
)

func main() {
	//=> Creating the game
	g := game.NewGame()

	//=> Running the game
	err := ebiten.RunGame(g)

	if err != nil {
		panic(err)
	}

}
