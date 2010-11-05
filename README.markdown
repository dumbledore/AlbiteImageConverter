Usage
-----

Just run it from within the directory containing images for conversion and it will automatically produce their **.ali* variants:

    java -jar AlbiteImageConverter-0.9.1

**Notes:**

1. Only PNG images will be converted, and all other files will be skipped
2. It works only with transparent PNGs, i.e. it copies the Alpha channel to the output grayscale **.ali* image. In other words, if your image is opaque, it will produce a completely white image. On the other hand, it will ignore any info on the RGB channels.

The **.ali* File Format
-----------------------

<table border="0" cellpadding="5">
  <thead>
    <tr>
      <th>Meaning</th>
      <th>Type</th>
      <th>Bytes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Magic number</td>
      <td>Integer</td>
      <td>4</td>
    </tr>
    <tr>
      <td>Image width</td>
      <td>Short</td>
      <td>2</td>
    </tr>
    <tr>
      <td>Image height</td>
      <td>Short</td>
      <td>2</td>
    </tr>
    <tr>
      <td>Image grayscale data</td>
      <td>byte[]</td>
      <td>width * height</td>
    </tr>
  </tbody>
</table>