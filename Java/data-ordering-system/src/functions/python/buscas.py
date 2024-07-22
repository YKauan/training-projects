import time

def linearSearch(arr, search):
        
        startTime = time.time()
        
        n = len(arr)
        
        found = ""
        
        for i in range(n):
            
            if(str(arr[i]) == search):
                found = arr[i]
                break
            
        
        endTime = time.time()
        
        global totalTime
        
        totalTime = endTime - startTime
        
        return found
    
def binarySearch(arr, search):
        
        startTime = time.time()
        
        inicio = 0
        fim = len(arr)-1
        
        meio = 0
        
        found = ""
        
        while (inicio <= fim):
        
            meio = (inicio + fim) / 2

            if(arr[meio] == search):
                found = arr[meio]
                break
            
            if(arr[meio] > search):
                fim = meio - 1
            else:
                inicio = meio + 1
                
        endTime = time.time()
        
        global totalTime
        
        totalTime = endTime - startTime
        
        return found

def totalTimeFunc():
    return totalTime