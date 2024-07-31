package main

import (
	"bufio"
	"fmt"
	"io"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"
)

const (
	CHECK_SAVED_SITES   = 1
	CHECK_SPECIFIC_SITE = 2
)

func main() {

	for {
		options()

		option := readOption()

		switch option {
		case 0:
			os.Exit(0)
		case 1:
			verifyWebSites(CHECK_SAVED_SITES)
		case 2:
			verifyWebSites(CHECK_SPECIFIC_SITE)
		case 3:
			addWebSite()
		case 4:
			printLog()
		default:
			fmt.Println("Invalid option")
			os.Exit(-1)
		}
	}
}

// Display options
func options() {
	fmt.Println("1 - Check status of saved websites")
	fmt.Println("2 - Consult specific website")
	fmt.Println("3 - Add website")
	fmt.Println("4 - View logs")
	fmt.Println("0 - Exit")
}

// Read option selected by user
func readOption() int {
	var option int
	fmt.Scan(&option)
	return option
}

// Verify if websites are online
func verifyWebSites(opc int) {
	fmt.Println("Checking websites...")

	switch opc {
	case CHECK_SAVED_SITES:
		websites := readWebSites()

		for _, site := range websites {

			resp, err := http.Get(site)
			if err != nil {
				fmt.Println("An error has occurred:", err)
			}

			if resp.StatusCode == 200 {
				fmt.Println("Site", site, "loaded successfully!")
				recordLog(site, true)
			} else {
				fmt.Println("Site", site, "with problems. Status code:", resp.StatusCode)
				recordLog(site, false)
			}

		}
	case CHECK_SPECIFIC_SITE:
		fmt.Println("Enter the website you want to check:")
		var site string
		fmt.Scan(&site)

		resp, err := http.Get(site)
		if err != nil {
			fmt.Println("An error has occurred:", err)
		}

		if resp.StatusCode == 200 {
			fmt.Println("Site", site, "loaded successfully!")
			recordLog(site, true)
		} else {
			fmt.Println("Site", site, "with problems. Status code:", resp.StatusCode)
			recordLog(site, false)
		}

	default:
		fmt.Println("Invalid option")
		os.Exit(-1)
	}
}

// Add website to the list
func addWebSite() {
	fmt.Println("Enter the URL of the website you want to add:")
	var site string
	fmt.Scan(&site)

	arq, err := os.OpenFile("websites.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		fmt.Println("An error has occurred:", err)
		return
	}

	arq.WriteString("\n" + site)

	arq.Close()
}

// Read websites from file
func readWebSites() []string {
	fmt.Println("Reading websites...")

	var websites []string

	arq, err := os.Open("websites.txt")
	if err != nil {
		fmt.Println("An error has occurred:", err)
		return nil
	}

	reader := bufio.NewReader(arq)

	for {
		line, err := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		websites = append(websites, line)
		if err == io.EOF {
			break
		}
	}

	arq.Close()

	return websites
}

// Record log
func recordLog(site string, status bool) {
	arq, err := os.OpenFile("log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		fmt.Println("An error has occurred:", err)
		return
	}

	arq.WriteString(time.Now().Format("02/01/2006 15:04:05") + " - " + "Site: " + site + " - Status: " + strconv.FormatBool(status) + "\n")

	arq.Close()
}

// Display log
func printLog() {
	arq, err := os.ReadFile("log.txt")
	if err != nil {
		fmt.Println("An error has occurred:", err)
		return
	}

	fmt.Println(string(arq))
}
