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
if( !empty($data->rfc) &&
    !empty($data->nom_usuario) &&
    !empty($data->telefono)&&
    !empty($data->correo)&&
    !empty($data->contrasena)){
        $parametros=array('rfc'=>$data->rfc,'nom_usuario'=>$data->nom_usuario,'telefono'=>$data->telefono, 'correo'=>$data->correo, 'contrasena'=>$data->contrasena);
        $res=insertUser($parametros);
        if($res!=null){
            http_response_code(201);
            echo json_encode(array(
                'status'=>true,
                'usr'=>$res,
                'msg'=>'Usuario Insertado'
            ));
        }else{
            http_response_code(503); 
            echo json_encode(array(
                'status'=>false,
                'msg'=>'No se puede Crear Usuario'
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
