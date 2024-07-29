package game

import (
	"math/rand"

	"github.com/YKauan/training-projects/tree/main/Go/JustGo/assets"
	"github.com/hajimehoshi/ebiten/v2"
)

type Meteor struct {
	image    *ebiten.Image
	speed    float64
	position Vector
}

// NewMeteor creates a meteor object
func NewMeteor() *Meteor {
	image := assets.MeteorSprites[rand.Intn(len(assets.MeteorSprites))]
	speed := (rand.Float64() * 13)
	position := Vector{
		X: rand.Float64() * screenWidth,
		Y: -100,
	}

	return &Meteor{
		image:    image,
		speed:    speed,
		position: position,
	}

}

// Update the meteor position
func (m *Meteor) Update() {
	m.position.Y += m.speed
}

// Draw the meteor on the screen
func (m *Meteor) Draw(screen *ebiten.Image) {
	op := &ebiten.DrawImageOptions{}

	op.GeoM.Translate(m.position.X, m.position.Y)

	screen.DrawImage(m.image, op)
}

// Collider returns the meteor collider
func (m *Meteor) Collider() Rect {
	bounds := m.image.Bounds()

	return NewRect(m.position.X, m.position.Y, float64(bounds.Dx()), float64(bounds.Dy()))
}
