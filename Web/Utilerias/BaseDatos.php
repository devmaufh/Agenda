<?php
try{
    //$Cn=new PDO("pgsql:host=192.168.0.1;port=5432;dbname=adyma;user=postgres;password=hola");
    $Cn=new PDO("pgsql:host=127.0.0.1;port=5432;dbname=adyma;user=postgres;password=12345678");
    $Cn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    //$Cn->exec("SET CHARACTER SET utf8");
    $Cn->exec("SET CLIENT_ENCODING TO 'UTF8';");
}catch(Exception $e){
    die("Error: " . $e->GetMessage());
}

// Función para ejecutar consultas SELECT
function Consulta($query)
{
    global $Cn;
    try{    
        $result =$Cn->query($query);
        $resultado = $result->fetchAll(PDO::FETCH_ASSOC); 
        $result->closeCursor();
        return $resultado;
    }catch(Exception $e){
        die("Error en la LIN: " . $e->getLine() . ", MSG: " . $e->GetMessage());
    }
}

//Función para mandar ejecutar un INSERT,UPDATE o DELETE
function Ejecuta($sentencia){
    global $Cn;
        try{
            $result =$Cn->query($sentencia);
            $result->closeCursor();
            return 1;
        }catch(Exception $e){
            die("Error en la LIN: " . $e->getLine() . ", MSG: " . $e->GetMessage());
        }        
return $insert;
}

//Función que manda ejecutar un INSERT regresando el consucutivo
function EjecutaConsecutivo($sentencia,$param){
    global $Cn;
        try{
            $result =$Cn->query($sentencia);
            $resultado = $result->fetchAll(PDO::FETCH_ASSOC);
            $result->closeCursor();
            return $resultado[0]["$param"];
        }catch(Exception $e){
            //die("Error en la LIN: " . $e->getLine() . ", MSG: " . $e->GetMessage());
            return 0;
        }        
return $insert;
}
function enc($var){
    $target=0.05;
    $c=8;
    $x=null;
    do{
        $c++;
        $start=microtime(true);
        $x=password_hash($var, PASSWORD_DEFAULT,["cost"=>$c]);
        $end=microtime(true);
    }while(($end-$start)<$target);
    return $x;
}
function checkHash($var,$hash){
    return password_verify($var,$hash);
}




function insertarUsuario($data){
    $c=$data['correo'];
    $nom=$data['nombre'];
    $pass=$data['pass'];
    if(count(checkUser($c))>0){
        $query="UPDATE users SET nombre='$nom', fecharegistro=current_date WHERE correo='$c' RETURNING correo";
    }else{
        $query="INSERT INTO users (correo,nombre,pass,fecharegistro) values('$c','$nom','$pass',current_date) RETURNING correo";
    }
    return EjecutaConsecutivo($query,"correo");
}
function checkUser($correo){
    $query="SELECT * FROM users WHERE correo= '$correo'";    
    return Consulta($query);
}
function llcheckCredentials($correo,$pass){
    $query="SELECT correo, pass FROM users WHERE correo='$correo'";
    $result=Consulta($query);
    if(count($result)>0){
        return true;
    }else{
        return false;
    }
}

function getContacts($data){
    if(checkCredentials($data['correo'],$data['pass'])){
        $correo=$data['correo'];
        $res=Consulta("SELECT * FROM contactos WHERE correouser='$correo'");
        $response=array();
        foreach ($res as $key => $value) {
            $response[$key]=$value;
        }
        return $response;
    }else{
        return false;
    }
}

function insertUser($data){
    $rfc = $data['rfc'];
    $nom_user = $data['nom_usuario'];
    $tel = $data['telefono'];
    $cor = $data['correo'];
    $con = $data['contrasena'];
    $query="INSERT INTO usuario (rfc, nom_usuario, telefono, correo, contrasena) VALUES ('$rfc', '$nom_user', '$tel', '$cor', '$con') RETURNING rfc";
    return EjecutaConsecutivo($query,"rfc");
}

function checkUsers($data){
    $correo = $data['correo'];
    $contrasena = $data['contrasena']; 
    $query="SELECT correo, contrasena FROM usuario WHERE correo='$correo' AND contrasena='$contrasena'";
    $result=Consulta($query);
    if(count($result)>0){
        return true;
    }else{
        return false;
    }
}

function insertRequest($data){
    $rfc = $data['rfc'];
    $id_dep = $data['id_dependencia'];
    $asi = $data['asistencia'];
    $id_sal = $data['id_sala'];
    $mat = $data['material'];
    $fec_eve = $data['fecha_evento'];
    $dur_eve = $data['duracion_evento']
    $query="INSERT INTO solcitud (rfc, id_dependencia, asistecia, id_sala, material, fecha_evento, duracion_evento)
            VALUES ('$rfc', '$id_dep', '$asi', '$id_sal', '$mat', '$fec_eve', '$dur_eve') RETURNING id_solicitud";
    return EjecutaConsecutivo($query,"id_solicitud");
}

function insertRoom($data){
    $nom_sala = $data['nom_sala'];
    $cap_sala = $data['cap_sala'];
    $query="INSERT INTO sala (nom_sala, cap_sala) VALUES ('$nom_sala', '$cap_sala') RETURNING id_sala";
    return EjecutaConsecutivo($query,"id_sala");
}

function getRoom(){
        $res=Consulta("SELECT * FROM sala");
        $response=array();
        foreach ($res as $key => $value) {
            $response[$key]=$value;
        }
        return $response;
}

function getEvent(){
    $res=Consulta("SELECT * FROM evento");
    $response=array();
    foreach ($res as $key => $value) {
        $response[$key]=$value;
    }
    return $response;
}
?>