<?php
require_once("Shape.php");
require_once("iResizable.php");

class Rectangle extends Shape
{
    private $height = 0;
    private $width = 0;

    public function __construct($in_height, $in_width)
    {
        $this->name = "Rectangle";
        $this->height = $in_height;
        $this->width = $in_width;
    }

    //Abstract method.
    public function calculateArea()
    {
        $this->area = $this->height * $this->width;
        return $this->area;
    }
}
?>