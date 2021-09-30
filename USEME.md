**Script commands that are supported by our application**

- create layer (adds a layer to the MultiLayer object)
- current (sets the specified layer as the current working layer)
- remove layer (removes the specified layer from the MultiLayer object)
- load all (loads all of the images from a MultiLayer object)
- load (loads a specified image file into the current layer)
- invisible (makes the specified layer invisible)
- show (makes the specified layer visible)
- blur (blurs the current layer's image)
- sharpen (sharpens the current layer's image)
- sepia (applies a sepia filter to the current layer's image)
- monochrome (applies a monochrome filter to the current layer's image)
- save all (exports the whole MultiLayer object into a specified folder)
- save (exports the topmost visible layer with the specified file name)
- end (ends the script)

**Conditions**
create layer or load all must be first action
current must be after layer exists
remove layer must be after layer exists
loading an image must be after setting a layer as current
invisible/show must be after a layer exists
blur, sharpen, sepia, monochrome can only be after loading an image or using load all
save and save all can only be called once layers have images
end is the last command in the script

**Example script of using them**

create layer first
current first
load landscape.jpg
blur
monochrome
sepia
sharpen
create layer second
current second
load flower.png
remove layer second
invisible first
show first
save landscape-modified.ppm
save all folder1
end


**Example script 2**
load all folder1
end