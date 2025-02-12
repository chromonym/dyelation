from PIL import Image
import os

colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

whiteMap = {
    (255, 255, 255, 255): 0,
    (239, 239, 239, 255): 1,
    (224, 229, 229, 255): 2,
    (199, 211, 211, 255): 3,
    (194, 194, 194, 255): 4,
    (168, 176, 177, 255): 5
}
colorMap = []

colorIm = Image.open("./colormap.png", mode="r")
width, height = colorIm.size

for h in range(16):
    thisColor = []
    for w in range(width):
        thisColor.append(colorIm.getpixel((w,h+1)))
    colorMap.append(thisColor)

dirs = ["curtain","lamp","sofa","stool","tall_stool"]

for dirr in dirs:
    directory = os.fsencode("./"+dirr+"/")
    for file in os.listdir(directory):
        filename = os.fsdecode(file)
        if filename.endswith(".png") and filename.startswith("white"):
            fileEnd = filename[5:]
            print(dirr+fileEnd)
            basisIm = Image.open(os.path.join(directory, file), mode="r")
            width, height = basisIm.size
            basisPix = basisIm.load()
            for color in range(16):
                newIm = Image.new(mode="RGBA", size=basisIm.size)
                newPix = newIm.load()
                for w in range(width):
                    for h in range(height):
                        bP = basisPix[w,h];
                        if bP in whiteMap:
                            newPix[w,h] = colorMap[color][whiteMap[bP]]
                        else:
                            newPix[w,h] = bP
                newIm.save("./"+dirr+"/"+colorNames[color]+fileEnd, format="png")
            continue
        else:
            continue
