<?php

// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
include_once('../../utilerias/BaseDatos.php');
//print_r(insertUser(array('correo'=>'nuevo@gamail.com','nombre'=>'nuevo','pass'=>'123')));
$data=json_decode(file_get_contents('php://input'));
if( !empty($data->nom_sala) &&
    !empty($data->capacidad_sala){
        $params=array('nom_sala'=>$data->nom_sala,'capacidad_sala'=>$data->capacidad_sala);
        $res=insertRoom($params);
        if($res!=null){
            http_response_code(201);
            echo json_encode(array(
                'status'=>true,
                'room'=>$res,
                'msg'=>'Sala Insertada'
            ));
        }else{
            http_response_code(503); //Servicio no disponible
            echo json_encode(array(
                'status'=>false,
                'msg'=>'No se puede Agregar la Sala'
            ));
        }
    }
else{
    http_response_code(404);
    echo json_encode(array(
        "status"=>false,
        "msg"=>'Verifique los Paramatros'
    ));
}
?>
