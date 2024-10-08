package models

import (
	"gopkg.in/validator.v2"
	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name  string `json:"name" validate:"nonzero"`
	Email string `json:"email" validate:"nonzero"`
}

func ValidateUser(user *User) error {
	if err := validator.Validate(user); err != nil {
		return err
	}
	return nil
}
