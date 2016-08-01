precision highp float;
uniform mat4 u_MVPMatrix;
attribute vec4 a_Position;
attribute  vec2 a_TexCoordinate;
varying vec2 v_TexCoordinate;
varying float v_pos_x;

void main()
{
	v_TexCoordinate = a_TexCoordinate;
	gl_Position = u_MVPMatrix * a_Position;	
	v_pos_x = a_Position.x;
	
}