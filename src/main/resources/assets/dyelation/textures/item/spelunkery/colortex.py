from PIL import Image
import os

colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

whiteMap = {} # actually red but
colorMap = []

colorIm = Image.open("./colormap.png", mode="r").convert("RGBA")
width, height = colorIm.size

#for w in range(width):
#    whiteMap[colorIm.getpixel((w,0))] = w
whiteMap[(220, 70, 60, 255)] = 0
whiteMap[(192, 53, 69, 255)] = 1
whiteMap[(165, 42, 60, 255)] = 2
whiteMap[(141, 30, 47, 255)] = 3
whiteMap[(110, 19, 33, 255)] = 4

print(whiteMap)
for h in range(16):
    thisColor = []
    for w in range(width):
        thisColor.append(colorIm.getpixel((w,h+1)))
    colorMap.append(thisColor)

dirs = ["glowstick", "glowstick_block"]

for dirr in dirs:
    directory = os.fsencode("./"+dirr+"/")
    for file in os.listdir(directory):
        filename = os.fsdecode(file)
        if filename.endswith("template.png"):
            fileEnd = filename[-4:]
            fileStart = filename[:-12]
            print(dirr+"/"+fileStart+"color"+fileEnd)
            basisIm = Image.open(os.path.join(directory, file), mode="r").convert("RGBA")
            width, height = basisIm.size
            basisPix = basisIm.load()
            for color in range(16):
                newIm = Image.new(mode="RGBA", size=basisIm.size)
                newPix = newIm.load()
                for w in range(width):
                    for h in range(height):
                        bP = basisPix[w,h]
                        if bP in whiteMap:
                            newPix[w,h] = colorMap[color][whiteMap[bP]]
                        else:
                            newPix[w,h] = bP
                newIm.save("./"+dirr+"/"+fileStart+colorNames[color]+fileEnd, format="png")
            continue
        else:
            continue

