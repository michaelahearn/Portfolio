<?php
    include("Shape.php");
    include("Triangle.php");
    include("Circle.php");
    include("Rectangle.php");
    session_start();
    $circle = new Circle($_POST['radius']);
    $rectangle = new Rectangle($_POST['rectHeight'], $_POST['rectWidth']);
    $triangle = new Triangle($_POST['triHeight'], $_POST['triWidth']);

    $SESSION["circle"] = $circle;
    $SESSION["rectangle"] = $rectangle;
    $SESSION["triangle"] = $triangle;

?>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Shape Area Calculator</title>
</head>
<body>
    <form name = "calculate" method = "post" action = "index.php">
        <fieldset>
            <legend>Circle</legend>
            <label>Radius </label>
            <input type = "text" name = "radius" value = "<?php echo $_POST['radius']?>"/>
        </fieldset>
        <fieldset>
            <legend>Rectangle</legend>
            <label>Height </label>
            <input type = "text" name = "rectHeight" value = "<?php echo $_POST['rectHeight']?>"/>
            <label>Width </label>
            <input type = "text" name = "rectWidth" value = "<?php echo $_POST['rectWidth']?>"/>
        </fieldset>
        <fieldset>
            <legend>Triangle</legend>
            <label>Height </label>
            <input type = "text" name = "triHeight" value = "<?php echo $_POST['triHeight']?>"/>
            <label>Width </label>
            <input type = "text" name = "triWidth" value = "<?php echo $_POST['triWidth']?>"/>
        </fieldset>
        <br />
        <p><input type = "submit" value = "Calculate"></p>

        <p><label>Grow/Shrink Percent </label>
            <input type = "text" name = "percent" /></p>
    </form>

    <?php

        echo "<h1>" . "Results" . "<br>";

        echo "<h2>" . "Shape: " . $rectangle->getName();
        echo "<h3>" . "Area: " . $rectangle->calculateArea();


        if($_POST['percent'] != "")
        {
            $SESSION["circle"]->Resize($_POST['percent']);
        }
        echo "<h2>" . "Shape: " . $SESSION["circle"]->getName();
        echo "<h3>" . "Area: " . $SESSION["circle"]->calculateArea();

        echo "<br />";

        if($_POST['percent'] != "")
        {
            $triangle->Resize($_POST['percent']);
        }
        echo "<h2>" . "Shape: " . $triangle->getName();
        echo "<h3>" . "Area: " . $triangle->calculateArea();
    ?>
</body>
</html>