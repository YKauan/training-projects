package main

import (
	"github.com/YKauan/training-projects/tree/main/Go/JustGo/game"
	"github.com/hajimehoshi/ebiten/v2"
)

func main() {
	g := game.NewGame()

	err := ebiten.RunGame(g)

	if err != nil {
		panic(err)
	}

}
