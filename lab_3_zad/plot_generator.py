import numpy as np
import matplotlib.pyplot as plt
import sys


def generate_plots(filename, data):

    types = 0
    if "jv" in filename:
        types = 2
    elif "js" in filename:
        types = 3

    print(types)
    i = 0
    while i < len(data):
        n = int(data[i])
        count = data[i + 1]
        i+=2
        values = [[-1 for __ in range(n)] for _ in range(types)]
        type = [-1 for _ in range(types)]
        for p in range(types):
            type[p] = data[i]
            i += 1
            for k in range(n):
                x = int(data[i])
                y = float(data[i + 1])
                values[p][x] = y
                i+=2
        for k in range(types):
            y_pos = np.arange(len(values[k]))
            if types==2:
                plt.bar(y_pos+(2*k-1)*0.2, values[k], 0.4, label=type[k])
            elif types==3:
                plt.bar(y_pos+(k-1)*0.3, values[k], 0.3, label=type[k])
        plt.ylabel('Waiting time [ms]', fontweight='bold', fontsize=10)
        plt.xlabel('Philosopher ID', fontweight='bold', fontsize=10)
        title = "For " + filename[-2:] + ", philosophers: " + str(n) + ", number of meals: " + str(count)
        plt.title(title)
        plt.legend()
        # plt.show()
        plt.savefig(filename[-2:] + "_" + str(n) + "_" + str(count))
        plt.clf()

file_names = ["results_jv", "results_js"]

data = dict()
for file in file_names:
    with open(file) as f:
        text = f.read()
        results = text.replace(",", '').replace("-", '').replace(";", '').replace('(milisec)', '').split()
        clean_results = []
        for s in results:
            if s == "ID" or s == "Simultaneous" or s == "Arbiter" or s == "Asymetrical":
                clean_results.append(s)
            else:
                try:
                    float(s)
                    clean_results.append(s)
                except ValueError as e:
                    pass
        print(clean_results)
        generate_plots(file, clean_results)
