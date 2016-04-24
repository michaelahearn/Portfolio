<?php
require_once("Shape.php");
require_once("iResizable.php");

class Triangle extends Shape implements iResizable
{
    private $height = 0;
    private $width = 0;

    public function __construct($in_height, $in_width)
    {
        $this->name = "Triangle";
        $this->height = $in_height;
        $this->width = $in_width;
    }

    //Abstract method.
    public function calculateArea()
    {
        $this->area = 1/2 * $this->height * $this->width;
        return $this->area;
    }

    //Interface method
    public function Resize($in_percent)
    {
        $this->height = $this->height * ($in_percent/100);
        return $this->height;
    }
}

?>