package game

import (
	"github.com/YKauan/training-projects/tree/main/Go/JustGo/assets"
	"github.com/hajimehoshi/ebiten/v2"
)

type Player struct {
	image             *ebiten.Image
	position          Vector
	game              *Game
	laserLoadingTimer *Timer
}

func NewPlayer(game *Game) *Player {
	image := assets.PlayerSprite

	bounds := image.Bounds()
	halfWidth := (float64(bounds.Dx()/2) / 2)

	position := Vector{
		X: (screenWidth / 2) - halfWidth,
		Y: 500,
	}

	return &Player{
		game:              game,
		image:             image,
		position:          position,
		laserLoadingTimer: NewTimer(12),
	}
}

func (p *Player) Update() {
	speed := 6.0

	if ebiten.IsKeyPressed(ebiten.KeyLeft) {
		p.position.X -= speed
	} else if ebiten.IsKeyPressed(ebiten.KeyRight) {
		p.position.X += speed
	}

	p.laserLoadingTimer.Update()

	if ebiten.IsKeyPressed(ebiten.KeySpace) && p.laserLoadingTimer.IsReady() {

		p.laserLoadingTimer.Reset()

		bounds := p.image.Bounds()
		halfWidth := (float64(bounds.Dx()) / 2)  // Divide by 2 to center the laser
		halfHeight := (float64(bounds.Dy()) / 2) // Divide by 2 to center the laser

		spawnPosition := Vector{
			p.position.X + halfWidth,
			p.position.Y - halfHeight,
		}

		laser := NewLaser(spawnPosition)
		p.game.AddLaser(laser)
	}
}

func (p *Player) Draw(screen *ebiten.Image) {
	op := &ebiten.DrawImageOptions{}

	//Positioning the player will be drawn on the screen
	op.GeoM.Translate(p.position.X, p.position.Y)

	//Draw the player on the screen
	screen.DrawImage(p.image, op)
}

func (p *Player) Collider() Rect {
	bounds := p.image.Bounds()

	return NewRect(p.position.X, p.position.Y, float64(bounds.Dx()), float64(bounds.Dy()))
}
