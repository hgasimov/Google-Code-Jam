'''
Created on Mar 27, 2013

@author: huseyngasimov
'''

def createArray(s, l):    
    arr = [[]] * l
    narr = s.split() # number array
    for n in range(l): arr[n] = [int(narr[n*2+1]), int(narr[n*2])]
    return arr        

def pruneLists(arr1, arr2):
    permitted1, permitted2 = set([]), set([])
    for k in range(len(arr1)): permitted1.add(arr1[k][0])
    for k in range(len(arr2)): permitted2.add(arr2[k][0])
    permitted = permitted1 & permitted2 # intersection 
    
    
    k = 0
    while k < len(arr2):
        if arr2[k][0] in permitted:            
            k += 1
        else:
            arr2.pop(k)
    
    k = 0
    while k < len(arr1):
        if arr1[k][0] in permitted:            
            k += 1
        else:
            arr1.pop(k)

        
def concatList(arr):
    k = 1
    while k < len(arr):
        if arr[k][0] == arr[k-1][0]:
            arr[k-1][1] += arr[k][1]
            arr.pop(k)
        else:
            k += 1

def maxBoxed(arr1, arr2):  
    if len(arr1) > 0 and len(arr2) > 0:   
             
        if arr1[0][0] == arr2[0][0]:
            if arr1[0][1] == arr2[0]:
                elm1 = arr1.pop(0)
                elm2 = arr2.pop(0)
                res = maxBoxed(arr1, arr2)
                arr1.insert(0, elm1)
                arr2.insert()
                return res
            elif arr1[0][1] < arr2[0][1]:
                margin = arr1[0][1]
                elm1 = arr1.pop(0)
                arr2[0][1] -= margin
                res = maxBoxed(arr1, arr2)
                arr1.insert(0, elm1)
                arr2[0][1] += margin
                return margin + res
            else:
                margin = arr2[0][1]
                elm2 = arr2.pop(0)
                arr1[0][1] -= margin
                res = maxBoxed(arr1, arr2)
                arr2.insert(0, elm2)
                arr1[0][1] += margin
                return margin + res            
        else:
            elm1 = arr1.pop(0)
            max1 = maxBoxed(arr1, arr2)            
            arr1.insert(0, elm1)
            
            elm2 = arr2.pop(0)
            max2 = maxBoxed(arr1, arr2)            
            arr2.insert(0, elm2)
            return max(max1, max2)
    else:
        return 0

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/BoxFactory/' 
input_filename = 'C-small-practice.in'
output_filename = 'C-small-practice.out'
DEBUG = False
testcase =  1

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for caseno in range(1, int(f.readline())+1):        
    strNM = f.readline().strip().split()            
    N, M = int(strNM[0]), int(strNM[1])   
    arr1 = createArray(f.readline().strip(), N)
    arr2 = createArray(f.readline().strip(), M)
    pruneLists(arr1, arr2)
    concatList(arr1)
    concatList(arr2)
    if DEBUG and caseno == testcase:
        print('arr1 size = ' + str(len(arr1)))
        print('arr2 size = ' + str(len(arr2)))
    
        
    res = maxBoxed(arr1, arr2)
    print('Case #' + str(caseno) + ': ' + str(res))
    fw.write('Case #' + str(caseno) + ': '  + str(res) + '\n')    
     
fw.close()
f.close()
