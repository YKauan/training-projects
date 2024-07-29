package game

import (
	"math/rand"

	"github.com/YKauan/training-projects/tree/main/Go/JustGo/assets"
	"github.com/hajimehoshi/ebiten/v2"
)

type Star struct {
	image    *ebiten.Image
	speed    float64
	position Vector
}

func NewStar() *Star {
	image := assets.StarsSprites[rand.Intn(len(assets.StarsSprites))]
	speed := (rand.Float64() * 13)
	position := Vector{
		X: rand.Float64() * screenWidth,
		Y: -100,
	}

	return &Star{
		image:    image,
		speed:    speed,
		position: position,
	}

}

func (m *Star) Update() {
	m.position.Y += m.speed
}

func (m *Star) Draw(screen *ebiten.Image) {
	op := &ebiten.DrawImageOptions{}

	op.GeoM.Translate(m.position.X, m.position.Y)

	screen.DrawImage(m.image, op)
}

func (m *Star) Collider() Rect {
	bounds := m.image.Bounds()

	return NewRect(m.position.X, m.position.Y, float64(bounds.Dx()), float64(bounds.Dy()))
}
