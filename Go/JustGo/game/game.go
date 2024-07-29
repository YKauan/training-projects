package game

import (
	"fmt"
	"image/color"

	"github.com/YKauan/training-projects/tree/main/Go/JustGo/assets"
	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/text"
)

type Game struct {
	player           *Player
	lasers           []*Laser
	meteors          []*Meteor
	stars            []*Star
	starsSpawnTimer  *Timer
	meteorSpawnTimer *Timer
	score            int
}

func (g *Game) AddLaser(laser *Laser) {
	g.lasers = append(g.lasers, laser)
}

func NewGame() *Game {
	g := &Game{
		meteorSpawnTimer: NewTimer(24),
		starsSpawnTimer:  NewTimer(25),
	}
	player := NewPlayer(g)
	g.player = player
	return g
}

// => Update game state 60 FPS
func (g *Game) Update() error {
	g.player.Update()

	for _, laser := range g.lasers {
		laser.Update()
	}

	g.meteorSpawnTimer.Update()
	if g.meteorSpawnTimer.IsReady() {
		g.meteorSpawnTimer.Reset()
		m := NewMeteor()
		g.meteors = append(g.meteors, m)
	}

	for _, meteor := range g.meteors {
		meteor.Update()
	}

	g.starsSpawnTimer.Update()
	if g.starsSpawnTimer.IsReady() {
		g.starsSpawnTimer.Reset()
		s := NewStar()
		g.stars = append(g.stars, s)
	}

	for _, star := range g.stars {
		star.Update()
	}

	for _, m := range g.meteors {
		if m.Collider().Intersects(g.player.Collider()) {
			g.Reset()
		}
	}

	for i, m := range g.meteors {
		for j, l := range g.lasers {
			if m.Collider().Intersects(l.Collider()) {
				g.meteors = append(g.meteors[:i], g.meteors[i+1:]...)
				g.lasers = append(g.lasers[:j], g.lasers[j+1:]...)
				g.score++
			}
		}
	}

	return nil
}

// => Draw game components
func (g *Game) Draw(screen *ebiten.Image) {
	g.player.Draw(screen)

	for _, laser := range g.lasers {
		laser.Draw(screen)
	}

	for _, meteor := range g.meteors {
		meteor.Draw(screen)
	}

	for _, star := range g.stars {
		star.Draw(screen)
	}

	text.Draw(screen, fmt.Sprintf("Score: %d", g.score), assets.FontUi, 20, 100, color.White)
}

func (g *Game) Layout(outsideWidth, outsideHeight int) (int, int) {
	return screenWidth, screenHeight
}

func (g *Game) Reset() {
	g.player = NewPlayer(g)
	g.lasers = nil
	g.meteors = nil
	g.score = 0
	g.meteorSpawnTimer.Reset()
}
