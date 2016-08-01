precision highp float;
precision highp int;
precision lowp sampler2D;
varying highp float v_pos_x;
varying highp vec2 v_TexCoordinate0;
varying highp vec2 v_TexCoordinate1;
varying highp vec2 v_TexCoordinate2;
varying highp vec2 v_TexCoordinate3;

uniform sampler2D u_Texture;
uniform sampler2D u_Texture1;
uniform sampler2D u_Texture2;
uniform sampler2D u_Texture3;

 
void main() {
    highp vec4 col0 = texture2D(u_Texture, v_TexCoordinate0);
    highp vec4 col1 = texture2D(u_Texture1, v_TexCoordinate1);
    highp vec4 col2 = texture2D(u_Texture2, v_TexCoordinate2);
    highp vec4 col3 = texture2D(u_Texture3, v_TexCoordinate3);
  		//float  xx = v_pos_x;
  		//if(xx >0.5){
			gl_FragColor = col0;
		//}else if( xx >=0.0){
	//		gl_FragColor = col1;
	//	}else if( xx >=-0.5){
 		//	gl_FragColor = col2;
		//}else{
	//		gl_FragColor = col3;
	//	}
 }