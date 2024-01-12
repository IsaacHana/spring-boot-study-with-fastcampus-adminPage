import SelectBox from "../ui/SelectBox";
import Pagination from "./Pagination";
import Table from "./Table";

const OPTIONS = [
  { value: "10", name: "10개" },
  { value: "20", name: "20개" },
  { value: "50", name: "50개" },
];

interface TableViewProps {
  onChangePageSize: (value: number) => void;
  onChangeCurrentElement: (value: number) => void;
  data: any;
  pagination: any;
  title: string;
  description: string;
  keys: string[];
  tableHeads: string[];
  isLoading: boolean;
}

const TableView: React.FC<TableViewProps> = ({
  onChangePageSize,
  onChangeCurrentElement,
  data,
  pagination,
  title,
  description,
  keys,
  tableHeads,
  isLoading,
}) => {
  return (
    <>
      <div className="flex flex-col p-4">
        <div className="flex flex-row mb-4 justify-between">
          <div className="flex flex-row align-middle gap-4 mb-2">
            <div className="text-xl text-white">{title}</div>
            <div className="text-white/60">{description}</div>
          </div>
          {data ? (
            <SelectBox
              defaultValue="10"
              options={OPTIONS}
              onChangePageSize={onChangePageSize}
            />
          ) : (
            <></>
          )}
        </div>

        <Table data={data} keys={keys} tableHeads={tableHeads} />

        {data ? (
          <Pagination
            pagination={pagination}
            onChangeCurrentElement={onChangeCurrentElement}
          />
        ) : (
          <></>
        )}
      </div>
    </>
  );
};

export default TableView;
