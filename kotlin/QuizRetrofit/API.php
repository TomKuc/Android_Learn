<?php

$conn = mysqli_connect("localhost", "root", "", "my_quiz_db");

$stmt = $conn->prepare("SELECT question,
		option1 , option2 , option3 ,
		option4 , correct_option
		FROM 
		math_tab ");
		

$stmt->execute();

$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

$questions_array = array();

while($stmt->fetch()){
	$temp = array();
	
	$temp['question'] = $question;
	$temp['option1'] = $option1;
	$temp['option2'] = $option2;
	$temp['option3'] = $option3;
	$temp['option4'] = $option4;
	$temp['correct_option'] = $correct_option;
	
	array_push($questions_array, $temp);
}

echo json_encode($questions_array);

?>