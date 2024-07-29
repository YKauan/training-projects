package game

const (
	screenWidth  = 800
	screenHeight = 600
)

type Vector struct {
	X, Y float64
}

type Rect struct {
	X, Y, Width, Height float64
}

// NewRect creates a new rectangle
func NewRect(x, y, width, height float64) Rect {
	return Rect{
		X:      x,
		Y:      y,
		Width:  width,
		Height: height,
	}
}

// Intersects returns true if the rectangle intersects with another rectangle
func (r Rect) Intersects(other Rect) bool {
	return r.X <= other.maxX() &&
		other.X <= r.maxX() &&
		r.Y <= other.maxY() &&
		other.Y <= r.maxY()
}

// maxX returns the maximum X value of the rectangle
func (r Rect) maxX() float64 {
	return r.X + r.Width
}

// maxY returns the maximum Y value of the rectangle
func (r Rect) maxY() float64 {
	return r.Y + r.Height
}
