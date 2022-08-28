import styled from "styled-components";

function Button({
  width,
  height,
  text,
  bgcolor,
  color,
  ftsize,
  bordercolor,
  hovercolor,
  padding,
  onClick,
}) {
  return (
    <Frame
      width={width}
      height={height}
      bgcolor={bgcolor}
      color={color}
      ftsize={ftsize}
      bordercolor={bordercolor}
      hovercolor={hovercolor}
      padding={padding}
      onClick={onClick}
    >
      {text}
    </Frame>
  );
}

const Frame = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
  width: ${(props) => props.width}px;
  height: ${(props) => props.height}px;
  padding: ${(props) => props.padding}px;
  background-color: ${(props) => props.bgcolor};
  color: ${(props) => props.color};
  font-size: ${(props) => props.ftsize}px;
  border: 1px solid ${(props) => props.bordercolor};
  cursor: pointer;
  :hover {
    background-color: ${(props) => props.hovercolor};
  }
`;

export default Button;
