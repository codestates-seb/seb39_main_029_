import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import DetailQnA from "../Components/DetailQnA";
import styled from "styled-components";

function DetailPage() {
  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <DetailQnA />
      </Wrapper>
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  height: 100vmax;
`;

export default DetailPage;
