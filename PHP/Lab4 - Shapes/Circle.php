<?php
require_once("Shape.php");
require_once("iResizable.php");

class Circle extends Shape implements iResizable
{
    private $radius = 0;

    public function __construct($in_radius)
    {
        $this->name = "Circle";
        $this->radius = $in_radius;
    }

    //Abstract method.
    public function calculateArea()
    {
        $this->area = pi() * pow($this->radius, 2);
        return $this->area;
    }

    //Interface method.
    public function Resize($in_percent)
    {
        $this->radius = $this->radius * ($in_percent/100);
        return $this->radius;
    }

    public function getRadius()
    {
        return $this->radius;
    }


}
?>