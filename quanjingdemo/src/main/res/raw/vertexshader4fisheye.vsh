attribute vec4 a_Position;
attribute vec2 a_TexCoordinate0;
attribute vec2 a_TexCoordinate1;
attribute vec2 a_TexCoordinate2;
attribute vec2 a_TexCoordinate3;

uniform mat4 u_MVPMatrix;
varying vec2 v_TexCoordinate0;
varying vec2 v_TexCoordinate1;
varying vec2 v_TexCoordinate2;
varying vec2 v_TexCoordinate3;
varying float v_pos_x;
 

void main() {
    gl_Position = u_MVPMatrix*a_Position;
    v_TexCoordinate0 = a_TexCoordinate0 ;
    v_TexCoordinate1 = a_TexCoordinate1 ;
    v_TexCoordinate2 = a_TexCoordinate2 ;
    v_TexCoordinate3 = a_TexCoordinate3 ;
    
    v_pos_x = a_Position.x;
    
 }
