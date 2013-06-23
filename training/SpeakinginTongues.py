'''
Created on Mar 14, 2013

@author: huseyngasimov
'''
working_dir = '/Users/huseyngasimov/Programs/eclipse/workspace/CodeJam/inputoutput_files/speakingintounges/' 
input_filename = 'A-small-practice.in'
output_filename = 'A-small-practice.out'


dictn = {}
dictn['y'], dictn['e'], dictn['q'], dictn[' '], dictn['z'] = 'a', 'o', 'z', ' ', 'q'


input = []
input.append('ejp mysljylc kd kxveddknmc re jsicpdrysi')
input.append('rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd')
input.append('de kr kd eoya kw aej tysr re ujdr lkgc jv')

outpt = []
outpt.append('our language is impossible to understand')
outpt.append('there are twenty six factorial possibilities')
outpt.append('so it is okay if you want to just give up')

for n in range(len(input)):
    g, s = input[n], outpt[n]
    gwc, swc = list(g), list(s) # convert to char array/list
    for j in range(min(len(gwc), len(swc))):
        dictn[gwc[j]] = swc[j]


print('dictnionary length = ' + str(len(dictn)))
print(dictn)

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')
for i in range(1, int(f.readline())+1):    
    carr = list(f.readline().strip())
    transl = []
    for c in carr: transl.append(dictn[c])
    outline = ''.join(transl) + '\n'     
    print(outline)
    fw.write('Case #' + str(i) + ': ' + outline)
    
f.close()
fw.close()



