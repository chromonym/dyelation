from PIL import Image
import os

colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

whiteMap = {} # actually red but
colorMap = []

colorIm = Image.open("./colormap.png", mode="r").convert("RGBA")
width, height = colorIm.size

for w in range(width):
    whiteMap[colorIm.getpixel((w,0))] = w

print(whiteMap)
for h in range(16):
    thisColor = []
    for w in range(width):
        thisColor.append(colorIm.getpixel((w,h+1)))
    colorMap.append(thisColor)

dirs = ["skull_candles"]

for dirr in dirs:
    directory = os.fsencode("./"+dirr+"/") # awning_template_side.png
    for file in os.listdir(directory):
        filename = os.fsdecode(file)
        if filename.endswith(".png") and filename.startswith("template"):
            fileEnd = filename[-4:]
            print(dirr+"/"+"color"+fileEnd)
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
                newIm.save("./"+dirr+"/"+colorNames[color]+fileEnd, format="png")
            continue
        else:
            continue

