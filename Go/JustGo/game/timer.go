package game

type Timer struct {
	currentTicks int
	targetTicks  int
}

// NewTimer creates a new timer object
func NewTimer(targetTicks int) *Timer {
	return &Timer{
		currentTicks: 0,
		targetTicks:  targetTicks,
	}
}

// Update the timer
func (t *Timer) Update() {
	if t.currentTicks < t.targetTicks {
		t.currentTicks++
	}
}

// IsReady returns true if the timer is ready
func (t *Timer) IsReady() bool {
	return t.currentTicks >= t.targetTicks
}

// Reset the timer
func (t *Timer) Reset() {
	t.currentTicks = 0
}
