import time
from tqdm import tqdm

def rBubbleSort(dados, nopc):
    
    startTime = time.time()
    
    n = len(dados[0])
    
    arrName         = dados[0]
    arrAge          = dados[1]
    arrCpf          = dados[2]
    arrCountry      = dados[3]
    arrImageCountry = dados[4]
    
    if(nopc == 0):
        description = "Ordenando por nome..."
        for i in tqdm(range(n), desc=description):
            
            for j in range(0, n-i-1):
                
                if arrName[j] > arrName[j+1]:
                    
                    arrAge[j], arrAge[j+1] = arrAge[j+1], arrAge[j]
                    
                    arrName[j], arrName[j+1] = arrName[j+1], arrName[j]

                    arrCpf[j], arrCpf[j+1] = arrCpf[j+1], arrCpf[j]

                    arrCountry[j], arrCountry[j+1] = arrCountry[j+1], arrCountry[j]

                    arrImageCountry[j], arrImageCountry[j+1] = arrImageCountry[j+1], arrImageCountry[j]
    elif(nopc == 1):
        description = "Ordenando por idade..."
        for i in tqdm(range(n), desc=description):
            
            for j in range(0, n-i-1):
                
                if arrAge[j] > arrAge[j+1]:
                    
                    arrAge[j], arrAge[j+1] = arrAge[j+1], arrAge[j]
                    
                    arrName[j], arrName[j+1] = arrName[j+1], arrName[j]

                    arrCpf[j], arrCpf[j+1] = arrCpf[j+1], arrCpf[j]

                    arrCountry[j], arrCountry[j+1] = arrCountry[j+1], arrCountry[j]

                    arrImageCountry[j], arrImageCountry[j+1] = arrImageCountry[j+1], arrImageCountry[j]
    elif(nopc == 2):
        description = "Ordenando por CPF..."
        for i in tqdm(range(n), desc=description):
            
            for j in range(0, n-i-1):
                
                if arrCpf[j] > arrCpf[j+1]:
                    
                    arrAge[j], arrAge[j+1] = arrAge[j+1], arrAge[j]
                    
                    arrName[j], arrName[j+1] = arrName[j+1], arrName[j]

                    arrCpf[j], arrCpf[j+1] = arrCpf[j+1], arrCpf[j]

                    arrCountry[j], arrCountry[j+1] = arrCountry[j+1], arrCountry[j]

                    arrImageCountry[j], arrImageCountry[j+1] = arrImageCountry[j+1], arrImageCountry[j]
    elif(nopc == 3):
        description = "Ordenando por pais..."
        for i in tqdm(range(n), desc=description):
            
            for j in range(0, n-i-1):
                
                if arrCountry[j] > arrCountry[j+1]:
                    
                    arrAge[j], arrAge[j+1] = arrAge[j+1], arrAge[j]
                    
                    arrName[j], arrName[j+1] = arrName[j+1], arrName[j]

                    arrCpf[j], arrCpf[j+1] = arrCpf[j+1], arrCpf[j]

                    arrCountry[j], arrCountry[j+1] = arrCountry[j+1], arrCountry[j]

                    arrImageCountry[j], arrImageCountry[j+1] = arrImageCountry[j+1], arrImageCountry[j]
    elif(nopc == 4):
        description = "Ordenando por imagem do pais..."
        for i in tqdm(range(n), desc=description):
            
            for j in range(0, n-i-1):
                
                if arrImageCountry[j] > arrImageCountry[j+1]:
                    
                    arrAge[j], arrAge[j+1] = arrAge[j+1], arrAge[j]
                    
                    arrName[j], arrName[j+1] = arrName[j+1], arrName[j]

                    arrCpf[j], arrCpf[j+1] = arrCpf[j+1], arrCpf[j]

                    arrCountry[j], arrCountry[j+1] = arrCountry[j+1], arrCountry[j]

                    arrImageCountry[j], arrImageCountry[j+1] = arrImageCountry[j+1], arrImageCountry[j]
    
    endTime = time.time()
    
    global totalTime
    
    totalTime = endTime - startTime
    
    return dados


def doubleLinkedList():
    return

def totalTimeFunc():
    return totalTime

class TreeNode:
    def __init__(self, name, age, cpf, country, imageCountry):
        self.name = name
        self.age = age
        self.cpf = cpf
        self.country = country
        self.imageCountry = imageCountry
        self.left = None
        self.right = None

class BinaryTreeSort:

    def binaryTreeSort(self, arrName, arrAge, arrCpf, arrCountry, arrImageCountry, opc):
        self.nodeList.clear()

        for i in range(len(arrName)):
            node = TreeNode(arrName[i], arrAge[i], arrCpf[i], arrCountry[i], arrImageCountry[i])
            self.insert(self.nodeList, node, opc)

    def treeOrdered(self):
        arr = [[], [], [], [], []]

        sortedList = []
        self.inorderTraversal(self.nodeList[0], sortedList)

        for node in sortedList:
            arr[0].append(node.name)
            arr[1].append(node.age)
            arr[2].append(node.cpf)
            arr[3].append(node.country)
            arr[4].append(node.imageCountry)

        return arr

    def insert(self, nodeList, newNode, opc):
        if not nodeList:
            nodeList.append(newNode)
            return

        current = nodeList[0]

        while True:
            if opc == 0:
                if newNode.name.lower() < current.name.lower():
                    if current.left is None:
                        current.left = newNode
                        break
                    current = current.left
                else:
                    if current.right is None:
                        current.right = newNode
                        break
                    current = current.right
            elif opc == 1:
                if newNode.age < current.age:
                    if current.left is None:
                        current.left = newNode
                        break
                    current = current.left
                else:
                    if current.right is None:
                        current.right = newNode
                        break
                    current = current.right
            elif opc == 2:
                if newNode.cpf.lower() < current.cpf.lower():
                    if current.left is None:
                        current.left = newNode
                        break
                    current = current.left
                else:
                    if current.right is None:
                        current.right = newNode
                        break
                    current = current.right
            elif opc == 3:
                if newNode.country.lower() < current.country.lower():
                    if current.left is None:
                        current.left = newNode
                        break
                    current = current.left
                else:
                    if current.right is None:
                        current.right = newNode
                        break
                    current = current.right
            elif opc == 4:
                if newNode.imageCountry.lower() < current.imageCountry.lower():
                    if current.left is None:
                        current.left = newNode
                        break
                    current = current.left
                else:
                    if current.right is None:
                        current.right = newNode
                        break
                    current = current.right

    def inorderTraversal(self, root, sortedList):
        if root is not None:
            self.inorderTraversal(root.left, sortedList)
            sortedList.append(root)
            self.inorderTraversal(root.right, sortedList)

    def search(self, key, opc):
        return self._search(self.nodeList[0], key, opc)

    def _search(self, root, key, opc):
        if root is None or (opc == 0 and root.name == key) or (opc == 1 and root.age == int(key)) or (opc == 2 and root.cpf == key) or (opc == 3 and root.country == key) or (opc == 4 and root.imageCountry == key):
            print("Encontrado:", getattr(root, ['name', 'age', 'cpf', 'country', 'imageCountry'][opc]))
            return root

        if (opc == 0 and key.lower() < root.name.lower()) or (opc == 1 and int(key) < root.age) or (opc == 2 and key.lower() < root.cpf.lower()) or (opc == 3 and key.lower() < root.country.lower()) or (opc == 4 and key.lower() < root.imageCountry.lower()):
            return self._search(root.left, key, opc)

        return self._search(root.right, key, opc)