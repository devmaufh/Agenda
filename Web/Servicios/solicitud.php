<?php

// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
include_once('../Utilerias/BaseDatos.php');
//print_r(insertUser(array('correo'=>'nuevo@gamail.com','nombre'=>'nuevo','pass'=>'123')));
$data=json_decode(file_get_contents('php://input'));
if( !empty($data->rfc)&&
    !empty($data->id_dependencia)&&
    !empty($data->asistencia)&&
    !empty($data->id_sala)&&
    !empty($data->material)&&
    !empty($data->fecha_evento)&&
    !empty($data->duracion_evento)){
        $params=array('rfc'=>$data->rfc, 'id_dependencia'=>$data->id_dependencia, 
        'asistencia'=>$data->asistencia, 'id_sala'=>$data->id_sala, 'material'=>$data->material, 
        'fecha_evento'=>$data->fecha_evento, 'duracion_evento'=>$data->duracion_evento);
        $res=insertRequest($params);
        if($res!=null){
            http_response_code(201);
            echo json_encode(array(
                'status'=>true,
                'sol'=>$res,
                'msg'=>'Solicitud Enviada'
            ));
        }else{
            http_response_code(503); 
            echo json_encode(array(
                'status'=>false,
                'msg'=>'Solicitud no Enviada'
            ));
        }
    }
else{
    http_response_code(404);
    echo json_encode(array(
        "status"=>false,
        "msg"=>'Verifique los Parametros'
    ));
}
?>
