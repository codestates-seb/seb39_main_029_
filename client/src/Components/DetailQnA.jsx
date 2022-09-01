import styled from "styled-components";
import "../index";
import Vote from "../Assets/Imgs/vote";
import ColorButton from "../Assets/ColorBtn";

function DetailQnA() {
  return (
    <Wrapper>
      <TitleWrapper>
        <div className="title">
          Swap first 2 rows of a 2d Python list without Numpy?
        </div>
        <div className="status">
          <div>
            <span className="ask">Asked</span>
            <span className="createdAt">2022.08.31</span>
          </div>
          <ColorButton mode="BLUE" text="Ask Question" />
        </div>
      </TitleWrapper>
      <QWrapper>
        <div className="qstatus">
          <Vote />
          <div className="vote">20</div>
        </div>
        <div className="qlist">
          <div className="Q">
            I am using a search pattern algorithm to find a pat(list) items in a
            txt(list). This function is supposed to return the index value of
            the list txt.
          </div>
          <div className="change">
            <button>Edit</button>
            <button>Delete</button>
          </div>
        </div>
      </QWrapper>
      <AWrapper>
        <div className="answerList">1 Answers</div>
        <div className="abox">
          <div className="astatus">
            <Vote className="rkt" />
            <div className="vote">13</div>
          </div>
          <div className="A">If you want to get index and item:</div>
        </div>
      </AWrapper>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  font-family: var(--sans-serif);
  font-size: var(--text-font);
  .vote {
    font-size: var(--ad-font);
    margin: 10px 0 0 0;
  }
`;
const TitleWrapper = styled.div`
  border-bottom: 1px solid hsl(210, 8%, 85%);
  padding: 20px;
  margin: 0 0 20px 20px;
  .title {
    font-size: var(--header-font);
    margin: 0 0 10px 0;
  }
  .status {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  > span {
    font-size: var(--normal-font);
  }
  .ask {
    color: var(--font-color-gray);
  }
  .createdAt {
    margin: 0 0 0 5px;
  }
`;
const QWrapper = styled.div`
  display: flex;
  padding: 20px;
  margin: 0 0 20px 20px;
  .qstatus {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .qlist {
    display: flex;
    flex-direction: column;
    margin: 0 0 0 30px;
  }
  .Q {
    margin: 0 0 20px 0;
  }
  .change {
    > button {
      margin: 0 10px 0 0;
      padding: 3px 8px;
      border: 1px solid hsl(210, 8%, 85%);
      border-radius: 5px;
    }
  }
`;
const AWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  margin: 0 0 20px 20px;
  .answerList {
    font-size: var(--ad-font);
    margin: 0 0 20px 0;
  }
  .astatus {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .abox {
    display: flex;
  }
  .A {
    margin: 0 0 0 30px;
  }
`;

export default DetailQnA;
