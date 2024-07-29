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

// => Add laser to the game
func (g *Game) AddLaser(laser *Laser) {
	g.lasers = append(g.lasers, laser)
}

// => Create a new game
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

	// Update lasers
	for _, laser := range g.lasers {
		laser.Update()
	}

	// Verify if the meteor is ready to spawn
	g.meteorSpawnTimer.Update()
	if g.meteorSpawnTimer.IsReady() {
		g.meteorSpawnTimer.Reset()
		m := NewMeteor()
		g.meteors = append(g.meteors, m)
	}

	// Update stars
	for _, meteor := range g.meteors {
		meteor.Update()
	}

	// Verify if the star is ready to spawn
	g.starsSpawnTimer.Update()
	if g.starsSpawnTimer.IsReady() {
		g.starsSpawnTimer.Reset()
		s := NewStar()
		g.stars = append(g.stars, s)
	}

	// Update stars
	for _, star := range g.stars {
		star.Update()
	}

	// Verify if player is colliding with meteor
	for _, m := range g.meteors {
		if m.Collider().Intersects(g.player.Collider()) {
			g.Reset()
		}
	}

	// Verify if laser is colliding with meteor
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

	// Draw lasers
	for _, laser := range g.lasers {
		laser.Draw(screen)
	}

	// Draw meteors
	for _, meteor := range g.meteors {
		meteor.Draw(screen)
	}

	// Draw stars
	for _, star := range g.stars {
		star.Draw(screen)
	}

	// Draw score
	text.Draw(screen, fmt.Sprintf("Score: %d", g.score), assets.FontUi, 20, 100, color.White)
}

// => Layout game screen
func (g *Game) Layout(outsideWidth, outsideHeight int) (int, int) {
	return screenWidth, screenHeight
}

// => Reset game state
func (g *Game) Reset() {
	g.player = NewPlayer(g)
	g.lasers = nil
	g.meteors = nil
	g.score = 0
	g.meteorSpawnTimer.Reset()
}
