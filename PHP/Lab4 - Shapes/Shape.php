<?php

abstract class Shape
{

protected $area = 0;//holds area of shape.
protected $name;//holds name of shape.

    abstract public function calculateArea();

    public function __construct($in_name)
    {
        $this->name = $in_name;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getArea()
    {
        return $this->area;
    }

}
?>
